import { v4 as uuid } from 'uuid';
import bigQuery from '../config/database.config.js';
import { createResponse } from '../helpers/createResponse.js';

const addTestResult = async (request) => {
	const { id, ...testData } = request;
	
	const options = {
		query: `SELECT * FROM \`hearity.users\` WHERE id = @value LIMIT 1`,
		params: { value: id },
	};

	const [users] = await bigQuery.query(options);

	if (!users || users.length === 0) {
		return createResponse(404, 'User not found');
	}

	const currentDate = new Date();
	const dd = String(currentDate.getUTCDate()).padStart(2, '0');
	const mm = String(currentDate.getUTCMonth() + 1).padStart(2, '0');
	const yyyy = currentDate.getUTCFullYear();

	const date = `${yyyy}-${mm}-${dd}`;

	const result = { id: uuid(), user_id: users[0].id, date, ...testData };
	await bigQuery.dataset('hearity').table('tests').insert(result);

	return createResponse(200, 'Success add test result');
};

const getTestResult = async (id) => {
	const options = {
		query: `SELECT * FROM \`hearity.tests\` WHERE user_id = @value`,
		params: { value: id },
	};
	const [testResult] = await bigQuery.query(options);

	if (testResult.length === 0) return createResponse(404, 'Forecast result not found');

	return createResponse(200, 'Success get all test result', { data: testResult });
};

export default { addTestResult, getTestResult };

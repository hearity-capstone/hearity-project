import { v4 as uuid } from 'uuid';
import bigQuery from '../config/database.config.js';
import { createResponse } from '../helpers/createResponse.js';

const addTestResult = async (request) => {
	const options = {
		query: `SELECT * FROM \`hearity.users\` WHERE id = @value LIMIT 1`,
		params: { value: request.id },
	};

	const [users] = await bigQuery.query(options);

	if (!users || users.length === 0) {
		return createResponse(404, 'User not found');
	}

	const currentDate = new Date();
	const dd = String(currentDate.getDate()).padStart(2, '0');
	const mm = String(currentDate.getMonth() + 1).padStart(2, '0');
	const yyyy = currentDate.getFullYear();

	const date = `${yyyy}-${mm}-${dd}`;

	const result = { id: uuid(), user_id: users[0].id, date, ...request };
	await bigQuery.dataset('hearity').table('tests').insert(result);

	return createResponse(200, 'Success add test result');
};

export default { addTestResult };

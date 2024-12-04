import { createResponse } from '../helpers/createResponse.js';
import Test from '../models/test.model.js';
import User from '../models/user.model.js';

const addTestResult = async (request) => {
	const user = await User.findOne({ _id: request.id });

	const currentDate = new Date();
	const dd = String(currentDate.getDate()).padStart(2, '0');
	const mm = String(currentDate.getMonth() + 1).padStart(2, '0');
	const yyyy = currentDate.getFullYear();

	const date = `${yyyy}-${mm}-${dd}`;

	const result = new Test({ user_id: user._id, date, ...request });
	return createResponse(200, 'Success add test result', { data: await result.save() });
};

export default { addTestResult };

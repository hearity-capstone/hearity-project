import getIdFromToken from '../helpers/getIdFromToken.js';
import testService from '../services/test.service.js';

const addTestResult = async (req, res, next) => {
	try {
		const id = getIdFromToken(req.headers);
		req.body.id = id;

		const response = await testService.addTestResult(req.body);
		res.status(response.statusCode).json(response);
	} catch (error) {
		next(error);
	}
};



export default { addTestResult };

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

const getTestResult = async (req, res, next) => {
	try {
		const id = getIdFromToken(req.headers);

		const response = await testService.getTestResult(id);
		res.status(response.statusCode).json(response);
	} catch (error) {
		next(error);
	}
};

export default { addTestResult, getTestResult };

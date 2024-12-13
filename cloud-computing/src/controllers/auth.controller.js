import authService from '../services/auth.service.js';

const register = async (req, res, next) => {
	try {
		const response = await authService.register(req.body);
		res.status(response.statusCode).json(response);
	} catch (error) {
		next(error);
	}
};

const login = async (req, res, next) => {
	try {
		const response = await authService.login(req.body);

		res.status(response.statusCode).json(response);
	} catch (error) {
		next(error);
	}
};

const refreshToken = async (req, res, next) => {
	try {
		const response = await authService.refreshToken(req.body);

		res.status(response.statusCode).json(response);
	} catch (error) {
		next(error);
	}
};

export default { register, login, refreshToken };

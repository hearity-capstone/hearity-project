import getIdFromToken from '../helpers/getIdFromToken.js';
import forecastService from '../services/forecast.service.js';

const getForecastResult = async (req, res, next) => {
	try {
		const id = getIdFromToken(req.headers);

		const response = await forecastService.getForecastResult(id);
		res.status(response.statusCode).json(response);
	} catch (error) {
		next(error);
	}
};

export default { getForecastResult };

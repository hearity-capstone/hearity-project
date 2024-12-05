import { createResponse } from '../helpers/createResponse.js';
import Forecast from '../models/forecast.model.js';

const getForecastResult = async (id) => {
	const forecastResult = await Forecast.find({ user_id: id });
	console.log(forecastResult.lenght < 1);
	if (forecastResult.lenght < 1) return createResponse(404, 'Forecast result not found');

	return createResponse(200, 'Success get forecast result', { data: forecastResult });
};

export default { getForecastResult };

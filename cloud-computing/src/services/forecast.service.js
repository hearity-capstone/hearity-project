import bigQuery from '../config/database.config.js';
import { createResponse } from '../helpers/createResponse.js';

const getForecastResult = async (id) => {
	const options = {
		query: `SELECT * FROM \`hearity.forecasting\` WHERE user_id = @value`,
		params: { value: id },
	};
	const [forecastResult] = await bigQuery.query(options);

	if (forecastResult.length === 0) return createResponse(404, 'Forecast result not found');

	return createResponse(200, 'Success get all forecast result', { data: forecastResult });
};

export default { getForecastResult };

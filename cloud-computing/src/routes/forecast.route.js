import express from 'express';
import forecastController from '../controllers/forecast.controller.js';
import { verifyToken } from '../middlewares/auth.middleware.js';

const forecastRouter = express.Router();

forecastRouter.get('/forecast-result', verifyToken, forecastController.getForecastResult);

export { forecastRouter };

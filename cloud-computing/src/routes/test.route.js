import express from 'express';
import testController from '../controllers/test.controller.js';
import { verifyToken } from '../middlewares/auth.middleware.js';

const testRouter = express.Router();

testRouter.post('/test-result', verifyToken, testController.addTestResult);
testRouter.get('/test-result', verifyToken, testController.getTestResult);

export { testRouter };

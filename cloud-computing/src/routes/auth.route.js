import express from 'express';
import authController from '../controllers/auth.controller.js';

const authRouter = new express.Router();
authRouter.post('/register', authController.register);
authRouter.post('/login', authController.login);
authRouter.post('/verify-token', authController.refreshToken);

export { authRouter };

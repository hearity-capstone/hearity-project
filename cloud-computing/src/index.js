import 'dotenv/config';
import express from 'express';
import { connect } from './config/database.config.js';
import { authRouter } from './routes/auth.route.js';

const app = express();
const port = process.env.PORT | 3000;

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

app.use('/api/auth', authRouter);

app.listen(port, () => {
	connect();
	console.log(`Server running at http://localhost:${port}`);
});

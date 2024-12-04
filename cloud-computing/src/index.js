import 'dotenv/config';
import express from 'express';
import { connect } from './config/database.config.js';
import { authRouter } from './routes/auth.route.js';
import { testRouter } from './routes/test.route.js';

const app = express();
const port = process.env.PORT | 3000;

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

app.use('/api/auth', authRouter);
app.use('/api/tests', testRouter);

app.get('/', (req, res) => {
	res.send('Welcome to Hearity API');
});

app.listen(port, () => {
	connect();
	console.log(`Server running at http://localhost:${port}`);
});

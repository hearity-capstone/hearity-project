import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
import { v4 as uuid } from 'uuid';
import bigQuery from '../config/database.config.js';
import { createResponse } from '../helpers/createResponse.js';

const register = async (request) => {
	const options = {
		query: `SELECT * FROM \`hearity.users\` WHERE email = @value LIMIT 1`,
		params: { value: request.email },
	};

	const [existingUsers] = await bigQuery.query(options);
	if (existingUsers.length > 0) {
		return createResponse(400, 'Email already exists.', {
			errors: { email: ['Some user have use this email.'] },
		});
	}

	const salt = bcrypt.genSaltSync(10);
	const hashedPassword = bcrypt.hashSync(request.password, salt);

	const newUser = {
		id: uuid(),
		...request,
		password: hashedPassword,
		create_at: new Date().toISOString(),
	};

	await bigQuery.dataset('hearity').table('users').insert(newUser);

	return createResponse(201, 'User has been created.');
};

const login = async (request) => {
	const options = {
		query: `SELECT * FROM \`hearity.users\` WHERE email = @value LIMIT 1`,
		params: { value: request.email },
	};

	const [users] = await bigQuery.query(options);
	if (users.length === 0) return { statusCode: 404, message: 'User not found' };

	const user = users[0];

	const isPasswordCorrect = await bcrypt.compare(request.password, user.password);
	if (!isPasswordCorrect) return createResponse(400, 'Wrong password or username');

	const payload = { id: user.id };
	const token = jwt.sign(payload, process.env.JWT_SECRET, {
		expiresIn: '1h',
	});

	const { password, ...otherDetails } = user;

	return createResponse(200, 'User logged', { data: { ...otherDetails }, token });
};

export default { register, login };

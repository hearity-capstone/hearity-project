import bcrypt from 'bcrypt';
import User from '../models/user.model.js';
import { createResponse } from '../helpers/createResponse.js';
import jwt from 'jsonwebtoken';

const register = async (request) => {
	const findUser = await User.findOne({ email: request.email });
	if (findUser) return createResponse(400, 'Email already exists.', { errors: { email: ['Some user have use this email.'] } });

	const salt = bcrypt.genSaltSync(10);
	const hash = bcrypt.hashSync(request.password, salt);

	const newUser = new User({
		...request,
		password: hash,
	});

  await newUser.save()

	return createResponse(201, 'User has been created.');
};

const login = async (request) => {
	const user = await User.findOne({ email: request.email });
	if (!user) return { statusCode: 404, message: 'User not found' };

	const isPasswordCorrect = await bcrypt.compare(request.password, user.password);
	if (!isPasswordCorrect) return createResponse(400, 'Wrong password or username');

	const payload = { id: user._id };
	const token = jwt.sign(payload, process.env.JWT_SECRET, {
		expiresIn: '1h',
	});

	const { password, ...otherDetails } = user._doc;

	return createResponse(200, 'User logged', { data: { ...otherDetails }, token });
};

export default { register, login };

import jwt from 'jsonwebtoken';

export const verifyToken = (req, res, next) => {
	const { authorization } = req.headers;
	const token = authorization && authorization.split(' ')[1];

	if (!token) {
		return res.status(401).json({ statusCode: 401, message: 'No token provided' });
	}

	jwt.verify(token, process.env.JWT_SECRET, (err, decoded) => {
		if (err) {
			return res.status(403).json({ statusCode: 403, message: 'Invalid token' });
		}

		req.user = decoded;
		next();
	});
};

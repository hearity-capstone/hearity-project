const getIdFromToken = (request) => {
	const token = request?.authorization?.split('.')[1];

	try {
		const decodedToken = Buffer.from(token, 'base64').toString('utf8');
		const data = JSON.parse(decodedToken);

		return data.id;
	} catch (error) {
		throw new Error('Failed to decode token or parse data.');
	}
};

export default getIdFromToken;
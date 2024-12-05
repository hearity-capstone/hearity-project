import mongoose from 'mongoose';

const forecastSchema = new mongoose.Schema({
	user_id: {
		type: mongoose.Schema.Types.ObjectId,
		ref: 'User',
	},
	date: {
		type: String,
		required: true,
	},
	left_freq_500_hz: {
		type: Number,
		required: true,
	},
	left_freq_1000_hz: {
		type: Number,
		required: true,
	},
	left_freq_2000_hz: {
		type: Number,
		required: true,
	},
	left_freq_4000_hz: {
		type: Number,
		required: true,
	},
	right_freq_500_hz: {
		type: Number,
		required: true,
	},
	right_freq_1000_hz: {
		type: Number,
		required: true,
	},
	right_freq_2000_hz: {
		type: Number,
		required: true,
	},
	right_freq_4000_hz: {
		type: Number,
		required: true,
	},
	AD: {
		type: Number,
		required: true,
	},
	AS: {
		type: Number,
		required: true,
	},
});

export default mongoose.model('Forecast', forecastSchema);

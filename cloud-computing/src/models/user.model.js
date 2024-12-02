import mongoose from 'mongoose';

const UserSchema = new mongoose.Schema(
	{
		first_name: {
			type: String,
			required: [true, 'Firstname field is required!'],
		},
		last_name: {
			type: String,
			required: [true, 'Lastname field is required!'],
		},
		email: {
			type: String,
			required: [true, 'Email field is required!'],
			unique: true,
			lowercase: true,
		},
		phone_number: {
			type: String,
			required: [true, 'Phone Number field is required!'],
			unique: true,
		},
		password: {
			type: String,
			required: [true, 'Password field is required!'],
			minlength: [8, 'Password must have at least 8 characters!'],
		},
		gender: {
			type: String,
			required: [true, 'Gender field is required!'],
		},
		address: {
			type: String,
			required: [true, 'Address field is required!'],
		},
		city: {
			type: String,
			required: [true, 'City field is required!'],
		},
	},
	{
		timestamps: {
			createdAt: 'created_at',
			updatedAt: 'updated_at',
		},
	}
);

export default mongoose.model('User', UserSchema);

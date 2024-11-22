import js from '@eslint/js';

export default [
	js.configs.recommended,

	{
		rules: {
			semi: ['error', 'always'],
			'no-console': 'off',
			'no-unused-vars': ['warn', { argsIgnorePattern: 'next', ignoreRestSiblings: true }],
		},
		languageOptions: {
			globals: {
				process: true,
				console: true,
			},
		},
	},
];

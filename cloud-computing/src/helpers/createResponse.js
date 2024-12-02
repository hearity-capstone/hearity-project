export const createResponse = (statusCode, message, ...restParams) => ({ statusCode, message, ...restParams[0] });

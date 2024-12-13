const { BigQuery } = require('@google-cloud/bigquery');
const User = require('../models/User');
const { OAuth2Client } = require('google-auth-library');

const bigqueryClient = new BigQuery();
const client = new OAuth2Client(process.env.GOOGLE_CLIENT_ID);

exports.googleLogin = (req, res) => {
    const sqlQuery = `SELECT name, COUNT(*) as count
                      FROM \`bigquery-public-data.samples.shakespeare\`
                      GROUP BY name
                      ORDER BY count DESC`;

    bigqueryClient.query(sqlQuery)
        .then(([rows]) => {
            res.status(200).json(rows);
        })
        .catch(error => {
            console.error('Error running query:', error);
            res.status(500).json({ error: 'Error running query' });
        });
};

exports.googleCallback = (req, res) => {
    res.redirect('/dashboard');
};

exports.logout = (req, res) => {
    req.logout();
    res.redirect('/');
};

exports.dashboard = (req, res) => {
    if (!req.user) {
        return res.redirect('/');
    }
    res.render('dashboard', { user: req.user });
};

exports.verifyGoogleToken = async (req, res) => {
    const { idToken } = req.body;

    try {
        const ticket = await client.verifyIdToken({
            idToken,
            audience: process.env.GOOGLE_CLIENT_ID,
        });
        const payload = ticket.getPayload();
        const userid = payload['sub'];
        const email = payload['email'];
        const fullName = payload['name']; // Full name
        const [firstName, lastName] = fullName.split(' '); // Split into first and last name

        let user = await User.findOne({ googleId: userid });
        if (!user) {
            user = new User({
                googleId: userid,
                email: email,
                firstName: firstName, // Save first name
                lastName: lastName,   // Save last name
            });
            await user.save();
        }

        res.status(200).json({ user: { id: user.googleId, email: user.email, firstName: user.firstName, lastName: user.lastName } });
    } catch (error) {
        console.error('Error verifying token:', error);
        res.status(401).json({ error: 'Invalid token' });
    }
};
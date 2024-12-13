const express = require('express');
const router = express.Router();
const userModel = require('../models/User.js'); 
const { OAuth2Client } = require('google-auth-library');

const client = new OAuth2Client(process.env.GOOGLE_CLIENT_ID);

router.get('/login', (req, res) => {
    res.redirect(`https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.GOOGLE_CLIENT_ID}&redirect_uri=${process.env.GOOGLE_REDIRECT_URI}&response_type=token&scope=profile email`);
});

router.get('/login/callback', async (req, res) => {
    const { id } = req.query; 

    if (!id) {
        return res.status(400).json({ error: 'No ID provided' });
    }

    try {
        const ticket = await client.verifyIdToken({
            idToken: id, 
            audience: process.env.GOOGLE_CLIENT_ID,
        });
        const payload = ticket.getPayload();
        const userid = payload['sub'];
        const email = payload['email'];
        const name = payload['name']; // Full name
        const [firstName, lastName] = name.split(' '); // Split full name into first and last

        let user = await userModel.findUserByGoogleId(userid);
        if (!user) {
            user = {
                googleId: userid,
                email: email,
                firstName: firstName, 
                lastName: lastName,   
            };
            await userModel.saveUser(user);
        }

        res.status(200).json({ user: { id: user.googleId, email: user.email, firstName: user.firstName, lastName: user.lastName } });
    } catch (error) {
        console.error('Error verifying token:', error);
        res.status(401).json({ error: 'Invalid token' });
    }
});

router.get('/logout', (req, res) => {
    res.redirect('/');
});

router.get('/dashboard', async (req, res) => {
    if (!req.user) {
        return res.redirect('/'); 
    }
    const user = await userModel.findUserByGoogleId(req.user.googleId);
    res.render('dashboard', { user });
});

router.post('/verify-token', async (req, res) => {
    const { idToken } = req.body;

    try {
        const ticket = await client.verifyIdToken({
            idToken: idToken, 
            audience: process.env.GOOGLE_CLIENT_ID,
        });
        const payload = ticket.getPayload();
        const userid = payload['sub'];
        const email = payload['email'];
        const name = payload['name']; 
        const [firstName, lastName] = name.split(' '); 

        let user = await userModel.findUserByGoogleId(userid);
        if (!user) {
            user = {
                googleId: userid,
                email: email,
                firstName: firstName, 
                lastName: lastName,   
            };
            await userModel.saveUser(user);
        }

        res.status(200).json({ user: { id: user.googleId, email: user.email, firstName: user.firstName, lastName: user.lastName } });
    } catch (error) {
        console.error('Error verifying token:', error);
        res.status(401).json({ error: 'Invalid token' });
    }
});

module.exports = router;
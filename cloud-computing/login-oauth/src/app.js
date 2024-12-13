const express = require('express');
const passport = require('passport');
const session = require('express-session');
const authRoutes = require('./routes/authRoutes');
require('dotenv').config();
require('./config/passport-setup');

const app = express();
app.use(express.json());

app.set('view engine', 'ejs');

app.use(session({ 
    secret: process.env.SESSION_SECRET, 
    resave: false, 
    saveUninitialized: true 
}));
app.use(passport.initialize());
app.use(passport.session());

app.use('/auth', authRoutes);

app.get('/', (req, res) => {
    res.send('<h1>Home</h1><a href="/auth/login">Login with Google</a>');
});

app.get('/dashboard', (req, res) => {
    if (!req.user) {
        return res.redirect('/');
    }
    res.render('dashboard', { user: req.user });
});

app.get('/logout', (req, res) => {
    req.logout();
    res.redirect('/');
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server berjalan di http://localhost:${PORT}`);
});
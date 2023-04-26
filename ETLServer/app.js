const express = require('express');

const morgan = require('morgan');
const dotenv = require('dotenv');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const helmet = require('helmet');
const nunjucks = require('nunjucks');
const hpp = require('hpp');
const path = require('path');
dotenv.config();

const { sequelize, Subscriber, Level_1, Level_2, Level_3, Level_4, Level_5, Over_5, } = require('./models');
const { Op } = require('sequelize');
const app = express();

sequelize.sync({ force: false })
    .then(() => {
        console.log('데이터베이스 연결 성공');
    })
    .catch((err) => {
        console.error(err);
    });


app.set('port', process.env.PORT || 3001);
app.set('view engine', 'html');
nunjucks.configure('views', {
    express: app,
    watch: true,
});
app.enable('trust proxy');
app.use(helmet({ contentSecurityPolicy: false }));
app.use(hpp());
app.use(morgan('dev'));
app.use(express.static(path.join(__dirname, 'views')));
app.use(cookieParser(process.env.COOKIE_SECRET));

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.get("/page", async (req, res) => {
    const subscribers = await Subscriber.findAll({});
    const level1 = await Subscriber.findAll({
        where: {
            level: 1
        }
    });
    const level2 = await Subscriber.findAll({
        where: {
            level: 2
        }
    });
    const level3 = await Subscriber.findAll({
        where: {
            level: 3
        }
    });
    const level4 = await Subscriber.findAll({
        where: {
            level: 4
        }
    });
    const level5 = await Subscriber.findAll({
        where: {
            level: 5
        }
    });
    const over5 = await Subscriber.findAll({
        where: {
            level: {
                [Op.gt]: 5
            }
        }
    });

    res.render("main", {
        count: subscribers.length, people: subscribers,
        level1: level1.length,
        level2: level2.length,
        level3: level3.length,
        level4: level4.length,
        level5: level5.length,
        over5: over5.length
    });
})

app.post('/etl', async (req, res) => {
    console.log("this is etl-server");
    console.log("body: ", req.body);
    const id = req.body.id;
    const name = req.body.name;
    const email = req.body.email;
    const level = req.body.level;
    const password = req.body.password;

    if (level === 1) {
        await Level_1.create({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 2) {
        await Level_2.create({
            name: name,
            email: email,    
            password: password,
        });
    } else if (level === 3) {
        await Level_3.create({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 4) {
        await Level_4.create({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 5) {
        await Level_5.create({
            name: name,
            email: email,
            password: password,
        });
    } else if (level > 5) {
        await Over_5.create({
            name: name,
            email: email,
            password: password,
        });
    } else {
        throw "error";
    }
    await Subscriber.create({
        name: name,
        email: email,
        level: level,
        password: password,
    });

    res.status(201).json("etl-post");
});

app.put('/etl', async (req, res) => {
    console.log("this is etl-server");
    console.log("put Data:", req.body);
    const id = req.body.id;
    const name = req.body.name;
    const email = req.body.email;
    const level = req.body.level;
    const password = req.body.password;
    if (level === 1) {
        await Level_1.update({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 2) {
        await Level_2.update({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 3) {
        await Level_3.update({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 4) {
        await Level_4.update({
            name: name,
            email: email,
            password: password,
        });
    } else if (level === 5) {
        await Level_5.update({
            name: name,
            email: email,
            password: password,
        });
    } else if (level > 5) {
        await Over_5.update({
            name: name,
            email: email,
            password: password,
        });
    } else {
        throw "error";
    }
    await Subscriber.update({
        name: name,
        email: email,
        level: level,
        password: password
    }, {
        where: {
            id: id
        }
    });

    res.status(201).json("etl-put");
});

app.get('/etl/all', (req, res) => {
    console.log("this is etl-all-server");
    res.status(201).json("etl-get-all");
});

app.get('/etl', (req, res) => {
    console.log("this is etl-server");
    console.log("get id: ", req.headers.id);
    res.status(201).json("etl-get");
});

app.delete('/etl', async (req, res) => {
    console.log("this is etl-server");
    console.log("delete id: ", req.headers.id);
    const id = req.headers.id;
    if (level === 1) {
        await Level_1.destroy({
            where: {
                id: id
            }
        });
    } else if (level === 2) {
        await Level_2.destroy({
            where: {
                id: id
            }
        });
    } else if (level === 3) {
        await Level_3.destroy({
            where: {
                id: id
            }
        });
    } else if (level === 4) {
        await Level_4.destroy({
            where: {
                id: id
            }
        });
    } else if (level === 5) {
        await Level_5.destroy({
            where: {
                id: id
            }
        });
    } else if (level > 5) {
        await Over_5.destroy({
            where: {
                id: id
            }
        });
    } else {
        throw "error";
    }
    await Subscriber.destroy({
        where: {
            id: id
        }
    });
    res.status(201).json("etl-delete");
});

app.use((req, res) => {
    let error = new Error(`${req.method} ${req.url} 라우터가 없습니다.`);
    error.statusCode = 404;
    res.status(error.statusCode).json({
        statusCode: error.statusCode,
        message: error.message
    });
});

app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
});

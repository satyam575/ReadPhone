const express = require('express');
const routes = require('./routes/api');
const bodyParser = require('body-parser');
//set up the app
const app = express();

app.use(bodyParser.json({limit: '10mb', extended: true}));
app.use(bodyParser.urlencoded({limit: '10mb', extended: true}))
//router
app.use(routes);

// app.get('/',function(req,res){
//     res.send("hey");
//     res.end();
// })

//listen for requuests
app.listen(4000,function(){
console.log('now listening for requests');
});

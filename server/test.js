console.log("hey");
const fs = require('fs');

fs.writeFile('callLogs.txt',"hello",function(err){
    if(err) throw err ;
    console.log('Saved!');

})
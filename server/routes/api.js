const express = require('express');
const router = express.Router();
var fs = require('fs');

router.get('/',function(req,res){
res.send('hello ana didi');
})

router.get('/marvel',function(req,res){
    res.send([{"callDate":"1532091902015","callDayTime":"Jul 20, 2018 6:35:02 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+919667731821"},{"callDate":"1532096584175","callDayTime":"Jul 20, 2018 7:53:04 PM","callDuration":"16","callType":"1","dir":"INCOMING","phoneNumber":"+918937050000"},{"callDate":"1532100502170","callDayTime":"Jul 20, 2018 8:58:22 PM","callDuration":"126","callType":"1","dir":"INCOMING","phoneNumber":"+917291931984"},{"callDate":"1532105298160","callDayTime":"Jul 20, 2018 10:18:18 PM","callDuration":"17","callType":"1","dir":"INCOMING","phoneNumber":"+919045195301"},{"callDate":"1532105741810","callDayTime":"Jul 20, 2018 10:25:41 PM","callDuration":"7","callType":"1","dir":"INCOMING","phoneNumber":"+919045195301"},{"callDate":"1532110408157","callDayTime":"Jul 20, 2018 11:43:28 PM","callDuration":"0","callType":"3","dir":"MISSED","phoneNumber":"+919045195301"},{"callDate":"1532110499866","callDayTime":"Jul 20, 2018 11:44:59 PM","callDuration":"24","callType":"1","dir":"INCOMING","phoneNumber":"+919045195301"},{"callDate":"1532112977924","callDayTime":"Jul 21, 2018 12:26:17 AM","callDuration":"0","callType":"3","dir":"MISSED","phoneNumber":"+919045195301"},{"callDate":"1532165545735","callDayTime":"Jul 21, 2018 3:02:25 PM","callDuration":"37","callType":"1","dir":"INCOMING","phoneNumber":"+919045195301"},{"callDate":"1532167413120","callDayTime":"Jul 21, 2018 3:33:33 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+918937050000"},{"callDate":"1532168960759","callDayTime":"Jul 21, 2018 3:59:20 PM","callDuration":"16","callType":"2","dir":"OUTGOING","phoneNumber":"+918937050000"},{"callDate":"1532170101899","callDayTime":"Jul 21, 2018 4:18:21 PM","callDuration":"3","callType":"1","dir":"INCOMING","phoneNumber":"+914071318559"},{"callDate":"1532170140463","callDayTime":"Jul 21, 2018 4:19:00 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+918937050000"},{"callDate":"1532170366133","callDayTime":"Jul 21, 2018 4:22:46 PM","callDuration":"16","callType":"1","dir":"INCOMING","phoneNumber":"+918937050000"},{"callDate":"1532172229227","callDayTime":"Jul 21, 2018 4:53:49 PM","callDuration":"10","callType":"1","dir":"INCOMING","phoneNumber":"+919045195301"},{"callDate":"1532172283623","callDayTime":"Jul 21, 2018 4:54:43 PM","callDuration":"31","callType":"1","dir":"INCOMING","phoneNumber":"+919045195301"},{"callDate":"1532176906168","callDayTime":"Jul 21, 2018 6:11:46 PM","callDuration":"5","callType":"1","dir":"INCOMING","phoneNumber":"+911409572231"},{"callDate":"1532177744749","callDayTime":"Jul 21, 2018 6:25:44 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+918477002475"},{"callDate":"1532179131687","callDayTime":"Jul 21, 2018 6:48:51 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+919045195301"},{"callDate":"1532179151040","callDayTime":"Jul 21, 2018 6:49:11 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+919045195301"},{"callDate":"1532179167429","callDayTime":"Jul 21, 2018 6:49:27 PM","callDuration":"21","callType":"2","dir":"OUTGOING","phoneNumber":"+918937050000"},{"callDate":"1532179187215","callDayTime":"Jul 21, 2018 6:49:47 PM","callDuration":"452","callType":"1","dir":"INCOMING","phoneNumber":"+918126041020"},{"callDate":"1532181199901","callDayTime":"Jul 21, 2018 7:23:19 PM","callDuration":"0","callType":"3","dir":"MISSED","phoneNumber":"+919045195301"},{"callDate":"1532189995419","callDayTime":"Jul 21, 2018 9:49:55 PM","callDuration":"0","callType":"2","dir":"OUTGOING","phoneNumber":"+918700439144"},{"callDate":"1532192333007","callDayTime":"Jul 21, 2018 10:28:53 PM","callDuration":"848","callType":"1","dir":"INCOMING","phoneNumber":"+918700439144"},{"callDate":"1532197858839","callDayTime":"Jul 22, 2018 12:00:58 AM","callDuration":"3600","callType":"1","dir":"INCOMING","phoneNumber":"+918700439144"},{"callDate":"1532201476811","callDayTime":"Jul 22, 2018 1:01:16 AM","callDuration":"0","callType":"3","dir":"MISSED","phoneNumber":"+918700439144"}
    ])
})

router.post('/retrieveCallLogs',function(req,res){
    
    req.header('Content-Type', 'application/json')
    console.log(req.get('sender'));
    fs.writeFile('./calllogs/callLogs' + req.get('sender') +   '.json',JSON.stringify(req.body),function(err){
        if(err) throw err ;
    })
    res.send({type:'post'})
})

router.post('/retrieveContacts',function(req,res){
    req.header('Content-Type', 'application/json')
  //  console.log(req.body);
    fs.writeFile('./contacts/contacts '+ req.get('sender')+ '.json',JSON.stringify(req.body),function(err){
        if(err) throw err ;
    })
    
    res.send({type:'post'})
})
router.post('/retrieveSms',function(req,res){
    
    req.header('Content-Type', 'application/json')
     console.log(req.get("sender"));    
    fs.writeFile('./sms/sms '+ req.get('sender') + '.json',JSON.stringify(req.body),function(err){
        if(err) throw err ;
    })
    
    res.send({type:'post'})
})

module.exports = router ;
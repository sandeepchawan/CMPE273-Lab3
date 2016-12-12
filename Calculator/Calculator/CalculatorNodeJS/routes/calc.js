var soap = require('soap');
var baseURL = "http://localhost:8080/CalculatorWS/services";

exports.calcValue = function(req,res) {
    var json_responses;
    var num1 = req.query.num1;
    var num2= req.query.num2;
    var operator = req.query.operator;
    var operation = "";

    var option = {
        ignoredNamespaces : true
    };

    switch(operator){
        case '+':
            operation =  "add";
            break;
        case '-':
            operation =  "subtract";
            break;
        case 'x':
            operation =  "multiply";
            break;
        case '÷':
            operation =  "divide";
            break;
        default:
            operation =  "invalid";
            break;
    }

    var url = baseURL+"/Calculate?wsdl";

    var args = {num1: num1,num2: num2, operation:operation};

    soap.createClient(url, option, function(err, client) {

        if (err) {
            console.log("Error is: ", err);
            throw err;
        }
        console.log("\n Client is: ", client);

        client.calculate(args, function(err, data) {

            if (err) {
                console.log("Err is: ", err);
                json_responses = {"statusCode" : 401};
                res.send(json_responses);
            }
            console.log("Data returned from server is: ", data);
            console.log("Data returned from server is: ", data.calculateReturn);
            var dataParsed = data.calculateReturn;
            console.log("dataParsed is: ", dataParsed);

            if(dataParsed && dataParsed.statusCode === 200){
                json_responses = {"statusCode" : 200, "result" : dataParsed.value};
                res.send(json_responses);
            }
            else{
                json_responses = {"statusCode" : 401};
                res.send(json_responses);
            }
        });
    });
};




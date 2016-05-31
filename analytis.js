"use strict";
var system = require('system');

function waitFor(testFx, onReady, timeOutMillis) {
    var maxtimeOutMillis = timeOutMillis ? timeOutMillis : 3000, //< Default Max Timout is 3s
        start = new Date().getTime(),
        condition = false,
        interval = setInterval(function() {
            if ( (new Date().getTime() - start < maxtimeOutMillis) && !condition ) {
                // If not time-out yet and condition not yet fulfilled
                condition = (typeof(testFx) === "string" ? eval(testFx) : testFx()); //< defensive code
            } else {
                if(!condition) {
                    // If condition still not fulfilled (timeout but condition is 'false')
                    console.log("'waitFor()' timeout");
                    phantom.exit(1);
                } else {
                    // Condition fulfilled (timeout and/or condition is 'true')
                    console.log("'waitFor()' finished in " + (new Date().getTime() - start) + "ms.");
                    typeof(onReady) === "string" ? eval(onReady) : onReady(); //< Do what it's supposed to do once the condition is fulfilled
                    clearInterval(interval); //< Stop this interval
                }
            }
        }, 250); //< repeat check every 250ms
};


var webpage = require('webpage');
var page = webpage.create();

page.viewportSize = { width: 1920, height: 1080 };

var prefix = 'sz';
var sn = 'xxxxxx';
var numRE = /^[0-9]+.?[0-9]*$/;
if (system.args.length > 1 && numRE.test(system.args[1])) {
	sn = system.args[1];
}

var url = 'http://?.baidu.com/?/' + prefix + sn + '.html';

var okCnt = 0;

var f = function (status) {
    // Check for page load success
    if (status !== "success") {
        console.log("Unable to access network");

    } else {
        waitFor(function() {
            // Check in the page if a specific element is now visible
            return page.evaluate(function() {
                return $("#d-chart").is(":visible");
            });
        }, function() {
        	console.log('@@@@@@@');
        	for (var x = 200; x < 1024; x = x + 2) {
        		console.log('+++++++');
        		page.sendEvent("mousemove", x, 440);
        		var s = Date.now();
        		for (var e = s; e-s<20; e = Date.now());
        		console.log('-------');
        	}
            
           console.log("The sign-in dialog should be visible now.");
           phantom.exit();
        });
    }
};

page.open(url, f);


'use strict';

var messageForm = document.querySelector('#messageForm');
var connectingElement = document.querySelector('#connecting');

var stompClient = null;

function connect() {

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

connect();

function onConnected() {

    stompClient.subscribe('/general/publicRoom', receiveUpdate);
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendUpdate(event) {

    var updateInfo = {
        id: document.querySelector('#username').innerText.trim(),
        status: document.querySelector('#status').innerText.trim()

    };
    stompClient.send("/app/update", {}, JSON.stringify(updateInfo));
    event.preventDefault();
}


function receiveUpdate(payload) {

    var message = JSON.parse(payload.body);

    if(message.status === 'OFF'){
        var a = document.getElementById("status");
        a.innerHTML = 'OFF';

    }
    else {
        var a = document.getElementById("status");
        a.innerHTML = 'ON';

    }
}

messageForm.addEventListener('submit', sendUpdate, true);

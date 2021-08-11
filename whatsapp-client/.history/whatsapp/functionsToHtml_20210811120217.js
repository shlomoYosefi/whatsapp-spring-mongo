// import { getAllMessagesByUserService } from "./service.mjs";

const urlMessages = "http://localhost:8082/api/v1/messages/"
const urlMessagesGroup = "http://localhost:8082/api/v1/messagesGroups/"
const urlUsers = "http://localhost:8082/api/v1/users/";
const urL


let currentUser = localStorage.getItem("username")
let content = document.getElementById("content")
let sendText = document.getElementById("sendText")
let ul = document.getElementById("ul");
let addGroup = document.getElementById("addGroup");
let addGroupSpan = document.getElementById("addGroupSpan");


let reciver = ""
let nameGroup=""
let users = [];
let allUsers =[]
let allGroups =[]
let messages = [];
let nameUser = "";
let activeNav = ""
let nameReciver=""

selectNav("messages")




function getUsers(val) {
    if(val){  
    allUsers =[]  
    val = JSON.parse(val);    
    for (i in val){
        if(val[i].email == currentUser)continue;
        allUsers.push(val[i])
    }
    writeToHtmlAllUsers();
}
}

function getAllGroupsValues(val){
    allGroups =[]
    val = JSON.parse(val);    
    for (i in val){
        allGroups.push(val[i])
    }
    writeToHtmlAllGroups();
}





function getAllMessagesOfSender(val) {    
    if(val){
    users = []   
    messages = []
    const valParse = JSON.parse(val)
    for (i in valParse) {

        nameSender = ""
        if (valParse[i].receiverId == localStorage.getItem("username")) {
            nameUser = valParse[i].senderId
            nameSender = valParse[i].senderName
        }
        else if (valParse[i].senderId == localStorage.getItem("username")) {
            nameUser = valParse[i].receiverId;
            nameSender = valParse[i].reciverName
        }
        if (users.includes(nameUser)) continue;        
        valParse[i].timeString = valParse[i].timeString
        if (valParse[i].text.length > 35) {
            valParse[i].text = valParse[i].text.substring(0, 25) + "  ...  "
        }
        users.push(nameUser)
        valParse[i]["name"] = nameSender
        
        messages.push(valParse[i])
        
    }    
    writeToHtmlUsers();
}
}


function getAllMessagesOfGroups(val){
    messages =[]
    const valParse = JSON.parse(val)
    for (i in valParse) {
        valParse[i].timeString = valParse[i].timeString
        messages.push(valParse[i])
    }
    writeToHtmlMessages()
}




function getAllMessagesByUser(val) {
    messages = []
    const valParse = JSON.parse(val)
    for (i in valParse) {
        valParse[i].timeString = valParse[i].timeString
        messages.push(valParse[i])
    }
    writeToHtmlMessages();
}


function addGroupName(){    
    let textName =document.getElementById("nameGrup").value;
    if(textName !=""){
        addGroup.style.display = "none";
        let dataJson = { "name": textName, "creator": currentUser}
        routerFunctions("POST",`${urlUsers}addGrup`,printResult,dataJson);
    }
}


function getCliceGroup(name){
    nameGroup = name
    routerFunctions("GET",`${urlMessages}getAllMessageOfGrup?name=${name}`,getAllMessagesOfGroups);
    addGroupSpan.innerHTML ="הוסף חבר לקבוצה"
    addGroupSpan.id = 'addUserToGroup'; 
    
    nameReciver =""
}



function getCliceUser(userName,index) {
    
    routerFunctions("GET", `${urlMessages}getAllMessageByUser?senderId=${currentUser}&userId=${userName}`, getAllMessagesByUser)    
    reciver = userName;
    nameReciver =document.getElementById(`spanName${index}`).innerHTML
    document.getElementById(activeNav).className = "";
    document.getElementById("messages").className = "active"
    activeNav = "messages";

}


function sendMessage() {
    if(nameGroup !=""){
        sendMessageToGroup()
        return
    }
    valueText = sendText.value;    
    if(valueText =="") return
    let dataJson = { "text": valueText, "senderId": currentUser, "receiverId": reciver }
    routerFunctions("POST", `${urlMessages}addMessage`, getAllMessagesByUser, dataJson)
    sendText.value = "";
}


function sendMessageToGroup() {    
    valueText = sendText.value;    
    if(valueText =="") return    
    let dataJson = { "text": valueText, "senderId": currentUser, "nameGrup": nameGroup }
    routerFunctions("POST", `${urlMessages}addMessageToGroup`, getAllMessagesOfGroups, dataJson)
    sendText.value = "";
}


function getAllUsers(){
    routerFunctions("GET",`${urlUsers}getAllUsers`,getUsers);
}


function getAllMessages(id){    
    routerFunctions("GET",`${urlMessages}getAllMessageOfSender?senderId=${id}`,getAllMessagesOfSender);
}


function getAllGroups(){        
    routerFunctions("GET",`${urlUsers}getAllGroups?username=${currentUser}`,getAllGroupsValues);
}



function removeMessage(id , currentUser,reciver){
    routerFunctions("DELETE",`${urlMessages}removeMessage?messageId=${id}&senderId=${currentUser}&reciver=${reciver}`,getAllMessagesOfSender);
}

function removeMessageGroup(id , currentUser , nameGroup){
    routerFunctions("DELETE",`${urlMessages}removeMessageFromGrup?messageId=${id}&senderId=${currentUser}&nameGroup=${nameGroup}`,getAllMessagesOfGroups);
}

function addUserGroup(id){
    let dataJson = { "nameGroup": nameGroup, "userName": id, "creator": currentUser }
    routerFunctions("POST", `${urlUsers}addMessageToGroup`, printResult, dataJson)
}




function selectNav(id) {
    switch (id) {
        
        case "messages":                    
                getAllMessages(currentUser)
                nameGroup =""
                addGroupSpan.id = "addGroupSpan"
                addGroupSpan.innerHTML ="הוסף קבוצה"

            break;
        case "group":
                getAllGroups()
                nameGroup =""
                addGroupSpan.id = "addGroupSpan"
                addGroupSpan.innerHTML ="הוסף קבוצה"

            break;
        case "users":
                getAllUsers()
                addGroupSpan.id = "addGroupSpan"
                addGroupSpan.innerHTML ="הוסף קבוצה"

            break;
    }
    document.getElementById("send-message").style.display = "none";
    content.style.height = "60vh";
    activeNav == "" ? "" : document.getElementById(activeNav).className = "";
    document.getElementById(id).className = "active"
    activeNav = id;
}



function deleted(id){
    
    const select = confirm ("אתה בטוח שברצונך למחוק את זה?")
    if(select){
        if(nameGroup !=""){
            removeMessageGroup(id,currentUser,nameGroup) ;
            return
        }
        removeMessage(`message${id}`,currentUser,reciver) ;
}
}


function menu(){    
    if(ul.style.display == "block"  || addGroup.style.display == "block" ) {
        ul.style.display = "none";
        addGroup.style.display = "none"
    }
    else ul.style.display = "block";
}

function selectMenue(id){
    if(addGroup.style.display = "block")addGroup.style.display = "none"
    switch(id){
        case "addGroupSpan":
            ul.style.display = "none";
            addGroup.style.display = "block"
            break;
        case "addUserToGroup" :
            document.getElementById("addUserToMenu").style.display = "block"

            dataUser()
            menu()
            break;   
    }

}




function printResult(val){
    alert(val);
    return
}




var xhttp = new XMLHttpRequest();

function routerFunctions(method, url, callBack, data) {

    xhttp.open(method, url, true);    
    if (data != null) {
        console.log(data);
        
        xhttp.send(JSON.stringify(data));
    }
    else {
        xhttp.send();
        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {  
                console.log(xhttp.responseText);              
                if (callBack) {
                    callBack(xhttp.responseText)
                }
            }
            else if (this.readyState == 4 && this.status > 200){
                printResult(xhttp.responseText)
            }
        }
    };
}












function writeToHtmlUsers() {
    content.innerHTML = "";
    for (let i = 0; i < users.length; i++) {
        content.innerHTML += `<div class="wrapMessage">
        <div id="${users[i]}" class="${users[i]}" onclick="getCliceUser(this.id,${i})">
        <div class="msgName">
            <span id="spanName${i}" style="color:red ;padding-right: 20px;">${messages[i].name}</span>
            <div class="time">
            <span id="spanHour">${messages[i].dateString}</span>
            <span id="spanHour">${messages[i].timeString}</span>
            </div>
        </div>
        <div class="msgText">
            <span id="msgText">${messages[i].text}</span>
            </div>
        </div>
        </div><hr>`
    }
}


function writeToHtmlAllUsers() {
    content.innerHTML = "";
    for (let i = 0; i < allUsers.length; i++) {
        content.innerHTML += `<div id="${allUsers[i].email}" class="wrapMessage" onclick="getCliceUser(this.id,${i})">
        <div class="${allUsers[i].email}"  >
        <div class="msgName">
            <span id="spanName${i}" >${allUsers[i].name} ${allUsers[i].lastname}</span>
            <div class="time">
            <span id="spanHour">${allUsers[i].phone}</span>
            </div>
        </div>
        </div>
        </div><hr>`
    }

}

function arrUser(val){
    allUsers =[]    
    val = JSON.parse(val);    
    for (i in val){
        allUsers.push(val[i])
    }  
    writeHtmlToAddUser()
    
}


function writeToHtmlMessages() {
    content.innerHTML ="";
    let senderOrReciver =""
    content.innerHTML = `<div id="nameReciver" style="color:red; padding-right: 20px;">${nameReciver}</div>`;
    for (let i =0; i <=messages.length-1; i++) {
        
        messages[i].senderId == currentUser? senderOrReciver="sender": senderOrReciver = "reciver"
        content.innerHTML += `
        <div class="wrapMessageMes" id= wrap${senderOrReciver}>
        <div id="${messages[i].id}" class="${messages[i].id}" style="padding:15px" ondblclick="deleted(this.id)">

        <div class="msg"  id=${senderOrReciver} >
            <div class="msgText">
            <span id="spanTextMsg">${messages[i].text}</span>
            </div>
                <div class="timeMsg">
                    <span id="spanHour">${messages[i].dateString}</span>
                    <span id="spanTime">${messages[i].timeString}</span>
            </div>
            </div>
            </div>
        </div>`
    }
    
    document.getElementById("send-message").style.display = "block";
    content.style.height = "40vh";
}


function writeToHtmlAllGroups(){
    content.innerHTML = "";
    console.log(allGroups);
    for (let i = 0; i < allGroups.length; i++) {
        content.innerHTML += `<div id="${allGroups[i].name}" class="wrapGroup" onclick="getCliceGroup(this.id)" >
        <div class="groupName" style="cursor: pointer;">
            <span id="spanName" style="font-size:30px" text-align:cebter ><b>${allGroups[i].name}</b></span>
        </div>
        </div><hr>`
    }
}

function dataUser(){
    routerFunctions("GET",`${urlUsers}getAllUsers`,arrUser);
}


function addUserToGroup(id){
    addUserGroup(id.split("addUser")[1])
    document.getElementById("addUserToMenu").style.display = "none"
    
}


 function writeHtmlToAddUser(){
   let htmlAddUser = document.getElementById("addUserToMenu")
    for (let i = 0; i < allUsers.length; i++) {
    htmlAddUser.innerHTML += `<span id="addUser${allUsers[i].email}" class="wrapMessage" onclick="addUserToGroup(this.id)">
        ${allUsers[i].name} ${allUsers[i].lastname}
        <span id="spanHour">${allUsers[i].phone}</span>
    <hr>`
}
}
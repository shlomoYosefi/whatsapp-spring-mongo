

const urlMessages = "http://localhost:82/messages/"
const senderId = localStorage.getItem("username")
const nameCurrentUser = localStorage.getItem("name") +" " + localStorage.getItem("lastname")

document.getElementById("title").innerHTML += `${nameCurrentUser} : הוואטספ של`


getAllMessages(senderId)









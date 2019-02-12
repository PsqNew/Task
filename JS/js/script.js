const btn = document.getElementById("btn");
const input = document.getElementById("input");
const Message = document.getElementById("message");
const discs = document.getElementById("array_location");
const right_Disc = document.getElementById("rightDiscs");
const left_Disc = document.getElementById("leftDiscs");
var sum_Left = 0;
var sum_Right = 0;

btn.addEventListener("click", Btn_Click);

function Btn_Click() {
    if (data_Input_Check(parseFloat(input.value))) {
        add_Array_Location(input.value);
    }
    else {
        alert("Enter data in the range from 0 to 20");
    }
}
function data_Input_Check(check){
    return check >= 0 && check <= 20 && check !== "" && (check ^ 0) === check;
}
discs.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'w3-margin') return;
    sum_Left += parseInt(target.innerHTML);
    add_To_Stec(target, "left", left_Disc);
    discs.children[target.id].remove();
    check_Barbell();
});
discs.addEventListener("contextmenu", function (event) {
    var target = event.target;
    if (target.parentElement.className !== 'w3-margin') return;
    sum_Right += parseInt(target.innerHTML);
    add_To_Stec(target, "right", right_Disc);
    discs.children[target.id].remove();
    check_Barbell();
});
function check_Barbell() {
    if (sum_Left === sum_Right && sum_Right !== 0) {
        Message.innerHTML = "Barbell ready";
    } else {
        Message.innerHTML = "Select the disk to add"
    }
}
right_Disc.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'w3-col s6 w3-center w3-border') return;
    sum_Right -= parseInt(target.innerHTML);
    add_Array_Location(target.innerHTML);
    right_Disc.children[target.id].remove();
    check_Barbell();
});
left_Disc.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'w3-col s6 w3-center w3-border') return;
    sum_Left -= parseInt(target.innerHTML);
    add_Array_Location(target.innerHTML);
    left_Disc.children[target.id].remove();
    check_Barbell();
});
function add_To_Stec(target, location, locArray) {
    let child = document.createElement('span');
    child.innerHTML = target.innerHTML;
    child.classList.add('w3-badge');
    child.id = location + '_disc_' + Date.now();
    locArray.appendChild(child);
}
function add_Array_Location(value) {
    var child = document.createElement('span');
    child.innerHTML = value;
    child.classList.add('w3-badge');
    child.id = 'disc_' + Date.now();
    discs.appendChild(child);
}

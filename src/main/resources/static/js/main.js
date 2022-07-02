let btnAll = document.querySelector('#btn-all')
let btnFinished = document.querySelector('#btn-finished')
let btnNotFinished = document.querySelector('#btn-notfinished')

/* Start handle click on nav buttons */
function handleBtnAll(categoryTask){
    btnAll.setAttribute('href', '/')
}

function handleBtnFinished(categoryTask){
    btnFinished.setAttribute('href', '/finished')
}

function handleBtnNotFinished(categoryTask){
    btnNotFinished.setAttribute('href', '/notfinished')
}
/* Start handle click on nav buttons */

/* Init class of nav buttons */
function initNavBtn(){
    let btn = document.querySelectorAll('.nav-btn')
    btn.forEach(b => b.className = 'btn btn-sm btn-outline-primary nav-btn d')
}

window.onload = function() {
    /* Start select nav button */
    let currentHref = window.location.href
    let pathname = new URL(currentHref).pathname;
    initNavBtn()
    if (pathname === '/'){
        btnAll.classList.add("nav-btn-selected")
    }else if (pathname === '/finished') {
        btnFinished.classList.add("nav-btn-selected")
    }else if (pathname === '/notfinished') {
        btnNotFinished.classList.add("nav-btn-selected")
    }
    /* End select nav button */

    /* Start handle done button */
    let btnDone = document.querySelectorAll('.btn-done')
    let taskItem = document.querySelectorAll('.task-item')
    for(let i = 0; i<btnDone.length; i++){
        if (btnDone[i].getAttribute('data-done')==='false') {
            btnDone[i].className = 'btn btn-success float-end btn-done fas fa-check'
            taskItem[i].className = 'task-item m-1 row bg-opacity-10 bg-danger mt-1 border-3 border-danger'
        }
        else {
            btnDone[i].className = 'btn btn-danger float-end btn-done fas fa-times'
            taskItem[i].className = 'task-item m-1 row bg-opacity-10 .bg-success mt-1 border-3 bg-success'
            let taskName = document.querySelectorAll('.task-name')[i]
            let text = taskName.textContent
            taskName.innerHTML = text.strike()
        }
        /* End handle done button */

    }

};
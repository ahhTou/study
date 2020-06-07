import {loginByUsernameAndPassword} from "./ajax.js"

let $submit = document.getElementById('submit')
let $iUser = document.getElementById('iUser')
let $iPass = document.getElementById('iPass')

let username_now = null
let password_now = null

window.onload = function () {
    $submit.addEventListener('click', function (e) {
        e.preventDefault()
        username_now = $iUser.value
        password_now = $iPass.value
        login(username_now, password_now)
    })
}

function login(username_check, password_check) {
    loginByUsernameAndPassword(username_check, password_check)
        .then(() => {
            console.log("> 登录成功了 准备跳转")
            window.localStorage.setItem("username",username_check)
            loginToSuccess()
        }, err => {
            console.log("> 失败了")
            loginToError()
        })
}

function loginToError() {
    alert('密码错误')
    window.localStorage.setItem("isLogin", "false")
}

function loginToSuccess() {
    window.location.href = '../index.html'
    window.localStorage.setItem("isLogin", "true")
}
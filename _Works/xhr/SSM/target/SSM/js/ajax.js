let url = "http://localhost:8080/SSM_war_exploded"


export function getNicknameByUsername(username) {
    return new Promise(((resolve, reject) => {
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/user/getUserBaseDataByUsername',
            contentType: 'application/json;charset=UTF-8',
            data: username,
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.exit) {
                    console.log("退出")
                    window.localStorage.setItem("isLogin", "false")
                    window.location.href = url + '/pages/login.html'
                    delCookie()
                } else {
                    resolve(data)
                }
            },
        })
    }))

}


export function delCookie() {
    return new Promise(((resolve, reject) => {
        document.cookie = "JSESSIONID=null; expires= expires=Thu, 01 Jan 1970 00:00:00 UTC,path=\",\""
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/login/exit',
            contentType: 'application/json;charset=UTF-8',
            data: 'del',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                console.log(data)
                if (data.exit) {
                    console.log("退出")
                    window.localStorage.setItem("isLogin", "false")
                    window.location.href = url + '/pages/login.html'
                }
            },
        })
    }))


}


export function loginByUsernameAndPassword(username_check, password_check) {
    let msg = {
        "username": username_check,
        "password": password_check
    }
    return new Promise(((resolve, reject) => {
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/login/check',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(msg),
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data === true || data === "true") {
                    resolve()
                } else {
                    delCookie()
                    reject()
                }
            }
        })
    }))

}

export function addByObj(obj) {
    return new Promise((resolve, reject) => {
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/list/addOne',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(obj),
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data === true || data === "true") {
                    resolve(data)
                } else {
                    reject("err")
                }
            }
        })
    })

}

export function getAllList(username) {
    return new Promise((resolve, reject) => {
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/list/getAll',
            contentType: 'application/json;charset=UTF-8',
            data: username,
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data !== null && data !== "") {
                    resolve(data)
                } else {
                    reject()
                }
            }
        })
    })


}

export function delOneByMovieId(movieId) {
    return new Promise((resolve, reject) => {
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/list/delOne',
            contentType: 'application/json;charset=UTF-8',
            data: movieId,
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data !== null && data !== "") {
                    resolve(data)
                } else {
                    reject()
                }
            }
        })
    })


}

export function updateOne(obj) {
    return new Promise((resolve, reject) => {
        $.ajax({
            // 编写json格式，设置属性和值
            url: url + '/list/updateOne',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(obj),
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data === true || data === "true") {
                    resolve(data)
                } else {
                    reject("err")
                }
            }
        })

    })
}
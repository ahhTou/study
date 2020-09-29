let obj = {
    '404': {
        code: '404',
        tip: '未找到该网页',
        color: '#f86565'
    },
    '4xx': {
        code: 'Error',
        tip: '客户端出现错误',
        color: '#d23f3f'
    },
    '405': {
        code: '405',
        tip: '错误的请求方式',
        color: '#65c99d'

    },
}


class ErrSetter {
    static make = (status) => {
        document.body.innerHTML = `
                        <div id="errViewsWrapper">
                        
                            <div id="wrapper">
                                <div id="code">
                                </div>
                                <div id="tip">
                                </div>
                            </div>
                            
                        </div>`

        let $code = document.getElementById('code')
        let $tip = document.getElementById('tip')
        let $wrapper = document.getElementById('errViewsWrapper')
        $code.innerText = obj[status].code
        $tip.innerText = obj[status].tip
        $wrapper.style.backgroundColor = obj[status].color
    }
}
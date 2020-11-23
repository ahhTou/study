function formatName(user) {
    return user.firstName + ' ' + user.lastName
}

const user = {
    firstName: 'ahhTou',
    lastName: 'Pro'
}

// 1. 特定属性可以使用 “” 也可以使用 {} 来使用某个变量
const element = (
    <h1 tabIndex="0">
        Hello, {formatName(user)}
    </h1>
)

// 2. 子元素
// 可以包含一个，也可以使用 () 包含多个
const e1 = <h1>I am `e1`</h1>
const e2 = (
    <div>
        <h1>hello!</h1>
        <h12>I AM `E2`</h12>
    </div>
)

// 3. 防止注入攻击
const title = response.potentiallyMaliciousInput;
// 直接使用是安全的：
const e3 = <h1>{title}</h1>;

ReactDOM.render(
    element,
    document.getElementById('root')
)

// 4. JSX 表示对象
// 以下两个代码等效
const e4_1 = (
    <h1 className="hello">
        Hello, World
    </h1>
)

const e4_2 = React.createElement(
    'h1',
    {className: 'hello'},
    'hello!'
)

// 实际上创建的代码 注意：这是简化过的结构
const e4_3 = {
    type: 'h1',
    props: {
        className: 'greeting',
        children: 'Hello, world!'
    }
};

function ActionLink0() {
    function handleClick(e) {
        e.preventDefault()
        alert('Clicked')
    }

    return (
        <a href="#" onClick={handleClick}>
            Click Me
        </a>
    )
}

class ActionLink extends React.Component {
    handleClick(e) {
        e.preventDefault()
        alert('es6 Clicked')
    }

    render() {
        return (
            <a href="#" onClick={this.handleClick}>
                Click Me
            </a>
        )
    }

}

class Toggle extends React.Component {
    constructor(props) {
        super(props)
        this.state = {isToggleOn: true}

        // 为了在回调中使用 `this`，这个绑定是必不可少的
        this.handleClick = this.handleClick.bind(this)
    }

    handleClick() {
        this.setState(state => ({
            isToggleOn: !state.isToggleOn
        }))
    }

    render() {
        return (
            <button onClick={this.handleClick}>
                {this.state.isToggleOn ? 'ON' : 'OFF'}
            </button>
        )
    }
}

ReactDOM.render(
    <Toggle/>,
    document.getElementById('root')
)

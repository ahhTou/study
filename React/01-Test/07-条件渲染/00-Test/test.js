class TButton extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            isDestroy: false,
            title: '点我改变',
            isHide: false,
        }
    }

    toDestroy() {
        this.setState({
            isDestroy: true,
        })
    }

    toChangeTitle() {
        this.setState({
            title: '我被改变了'
        })
    }

    toHide() {
        this.setState({
            isHide: true,
        })
    }

    render() {
        if (this.state.isDestroy) return null
        return (
            <div>
                {!this.state.isHide && <button onClick={() => this.toDestroy()}>摧毁全部</button>}
                {!this.state.isHide && <button onClick={() => this.toChangeTitle()}>{this.state.title}</button>}


                <button onClick={() => this.toHide()}>隐藏其他按钮</button>
            </div>
        )
    }

}


ReactDOM.render(
    <TButton/>,
    document.getElementById('root')
)

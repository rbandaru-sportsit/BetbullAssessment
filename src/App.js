import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, NavLink } from 'react-router-dom';
import { bindActionCreators } from "redux";
import { connect } from 'react-redux';
import './assets/css/bootstrap.min.css';
import './assets/css/main-styles.min.css';
import headerLogo from './assets/images/logo-header.svg';
import './App.css';
import StartPage from './components/startpage/StartPage';
import GamePage from './components/gamepage/GamePage';
import ScorePage from './components/scorepage/ScorePage';

class App extends Component {
    render() {
        return (
            <Router>
                <div>
                    <header>
                        <nav className="navbar px-0 shadow">
                            <div className="container-fluid justify-content-end">
                                <div className="col pl-0 text-center">
                                    <div className="navbar-brand">
                                        <img src={headerLogo} alt="logo"/>
                                    </div>
                                </div>
			                 </div>
                        </nav>
                    </header>
                        <div>
                            <Switch>
                                <Route exact path={'/'} render={(props) => <StartPage {...props} />} />
                                <Route exact path={'/start'} render={(props) => <StartPage {...props} />} />
                                <Route path={'/game'} render={(props) => <GamePage {...props} />} />
                                <Route path={'/score'} render={(props) => <ScorePage {...props} />} />
                            </Switch>
                        </div>

                    <footer className="">
                        <div className="SM-footer">
                            <div className="container-fluid">
                                <div className="row align-items-center">
                                    <div className="col-12 text-center">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </footer>

                </div>
            </Router>
        );
    }
}

function mapStateToProps(state) {
    return {

    };
}
function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        actions: bindActionCreators({
        }, dispatch)
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(App);
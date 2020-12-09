import React, { Component } from 'react';
import { bindActionCreators } from "redux";
import { connect } from 'react-redux';
import { BrowserRouter as Router, Route, Switch, NavLink } from 'react-router-dom';

import {
    goBacktToGamePage, goBacktToStartPage, getUpdatedDataFromStorage
} from './ScorePageSlice';

class ScorePage extends Component {
    componentWillMount() {
        this.props.dispatch(getUpdatedDataFromStorage());
    }
    render() {
        return (
            <main role="main" className="SM-home">
                <div className="SM-section px-3">
                    <table className="table table-bordered SM-txt-white text-center">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Score</th>
                            </tr>
                        </thead>
                        <tbody data-scoretable='component-scorePageRecordsDisplay'>
                            {
                                this.props.getAllUsersDetails && this.props.getAllUsersDetails.map((item, index) => {
                                    return <tr key={index}>
                                        <td>{item.userName}</td>
                                        <td>{item.score}</td>
                                    </tr>
                                })
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <div className="col-6 pl-3 pr-1">
                            <button className="btn SM-btnPrimary SM-btnMedium w-100 mt-4" onClick={() => this.props.dispatch(goBacktToGamePage(this.props))}>Continue Playing</button>
                        </div>
                        <div className="col-6 pr-3 pl-1">
                            <button className="btn SM-btnPrimary SM-btnMedium w-100 mt-4" onClick={() => this.props.dispatch(goBacktToStartPage(this.props))}>Reset Game</button>
                        </div>
                    </div>
                </div>
            </main>
        );
    }


}


function mapStateToProps(state) {
    return {
        userSelectedName: state.startpage.userSelectedName,
        userScore: state.gamepage.userScore,
        getAllUsersDetails: state.scorepage.getAllUsersDetails
    };
}
function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        actions: bindActionCreators({
        }, dispatch)
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(ScorePage);

import React, { Component } from 'react';
import { bindActionCreators } from "redux";
import { connect } from 'react-redux';
import { BrowserRouter as Router, Route, Switch, NavLink } from 'react-router-dom';
import { staticCardsInfoArray } from '../../sharedfiles/constants';

import {
    getCardsData, getNextCardsData, gotoScoreBoardPage, resetData
} from './GamePageSlice';

class GamePage extends Component {
    componentWillMount() {
        this.props.dispatch(getCardsData(staticCardsInfoArray, this.props.userSelectedName));
    }
    startButtonClick() {
        this.props.dispatch(getCardsData(staticCardsInfoArray));
    }

    nextButtonClick() {
        this.props.dispatch(getNextCardsData(staticCardsInfoArray, this.props.getDisplayData, this.props.userScore));
    }

    gotoScoreBoardPage() {
        this.props.dispatch(gotoScoreBoardPage(this.props));
    }

    render() {
        var getDataFromLocalStorage = [];
        getDataFromLocalStorage = JSON.parse(localStorage.getItem('userData'));
        var getUserNameFromStorage = localStorage.getItem('userName');
        var getUsersDetails = {
            "userName": this.props.userSelectedName === '' ? getUserNameFromStorage : this.props.userSelectedName,
            "score": this.props.userScore
        };
        var storUsersDetails = [];
        storUsersDetails.push(getUsersDetails);
        if (getDataFromLocalStorage !== null && getDataFromLocalStorage.length > 0) {
            for (let s = 0; s < getDataFromLocalStorage.length; s++) {
                if (getDataFromLocalStorage[s].userName === getUsersDetails.userName) {
                    getDataFromLocalStorage[s].score = getUsersDetails.score;
                }
            }
            localStorage.setItem('userData', JSON.stringify(getDataFromLocalStorage));
        } else {
            localStorage.setItem('userData', JSON.stringify(storUsersDetails));
        }
        return (
            <main role="main" className="SM-play">
                <div className="SM-section SM-play-section">
                    <div className="SM-deck">
                        {
                            this.props.getDisplayData && this.props.getDisplayData.map((item, index) => {
                                return <div className="SM-card" key={index}>
                                    <input type="checkbox" defaultChecked />
                                    {item.isCardDisplay === true ? <div className={"SM-front " + item.code}></div> : <div className={"SM-back " + item.code}></div>}
                                </div>
                            })
                        }
                    </div>
                    <div className="row">
                        {this.props.isDisplayNextButton ? <div className="col text-center">
                            <button className="btn SM-btnPrimary SM-btnMedium w-50 mt-4" onClick={() => this.nextButtonClick()}>Pick Next</button>
                        </div> : ""}</div>
                    {this.props.isDisplayNewGameButton ? <div className="text-center SM-score">Your Score is : <span>{this.props.userScore}</span></div> : ""}
                    <div className="row">
                        {this.props.isDisplayNewGameButton ? <div className="col-6 pl-3 pr-1">
                            <button className="btn SM-btnPrimary SM-btnMedium w-100 mt-4" onClick={() => this.startButtonClick()}>New Game</button>
                        </div> : ""}
                        {this.props.isDisplayNewGameButton ? <div className="col-6 pr-3 pl-1">
                            <button className="btn SM-btnPrimary SM-btnMedium w-100 mt-4" onClick={() => this.gotoScoreBoardPage()}>Score Board</button>
                        </div> : ""}
                    </div>
                </div>
            </main>
        );
    }


}


function mapStateToProps(state) {
    return {
        getDisplayData: state.gamepage.displayFilteredData,
        isDisplayPlayButton: state.gamepage.isDisplayPlayButton,
        isDisplayNextButton: state.gamepage.isDisplayNextButton,
        isDisplayNewGameButton: state.gamepage.isDisplayNewGameButton,
        userScore: state.gamepage.userScore,
        isCardDisplay: state.gamepage.isCardDisplay,
        userSelectedName: state.startpage.userSelectedName
    };
}
function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        actions: bindActionCreators({
        }, dispatch)
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(GamePage);

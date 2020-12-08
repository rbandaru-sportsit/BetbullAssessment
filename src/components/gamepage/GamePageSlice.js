import { createSlice } from '@reduxjs/toolkit';


export const GamePageSlice = createSlice({
    name: 'gamepage',
    initialState: {
        displayFilteredData: [],
        isDisplayPlayButton: true,
        isDisplayNextButton: false,
        isDisplayNewGameButton: false,
        userScore: 0
    },
    reducers: {
        getFilteredData: (state, action) => {
            state.displayFilteredData = action.payload.getRandomData;
            state.isDisplayPlayButton = action.payload.isDisplayPlayButton;
            state.isDisplayNextButton = action.payload.isDisplayNextButton;
            state.isDisplayNewGameButton = action.payload.isDisplayNewGameButton;
        },
        resetData: (state, action) => {
            state.displayFilteredData = [];
            state.isDisplayPlayButton = true;
            state.isDisplayNextButton = false;
            state.isDisplayNewGameButton = false;
        },

        updateUserScore: (state, action) => {
            state.userScore = action.payload;
        },

        updateGameStatus: (state, action) => {
            state.isDisplayNewGameButton = action.payload;
        },

        updateCardDisplayStatus: (state, action) => {
            if (state.displayFilteredData.length > 0) {
                for (let u = 0; u < state.displayFilteredData.length; u++) {
                    if (state.displayFilteredData[u].code === action.payload) {
                        state.displayFilteredData[u].isCardDisplay = true;
                    }
                }
            }
        }
    },
});

export const { getFilteredData, resetData, updateUserScore, updateCardDisplayStatus, updateGameStatus } = GamePageSlice.actions;

export const getCardsData = (data, name) => dispatch => {
    dispatch(resetData())
    if (data.length > 0) {
        var payloadData = { getRandomData: [], isDisplayPlayButton: true, isDisplayNextButton: false, isDisplayNewGameButton: false };
        var randomElement = data[Math.floor(Math.random() * data.length)];
        payloadData.getRandomData.push(randomElement);
        payloadData.isDisplayPlayButton = false;
        payloadData.isDisplayNextButton = true;
        payloadData.isDisplayNewGameButton = false;
        dispatch(getFilteredData(payloadData));
        setTimeout(() => {
            dispatch(updateCardDisplayStatus(randomElement.code))
        }, 1000);
        var getDataFromLocalStorage = [];
        getDataFromLocalStorage = JSON.parse(localStorage.getItem('userData'));
        if (getDataFromLocalStorage !== null && getDataFromLocalStorage.length > 0) {
            if (name === '') {
                name = localStorage.getItem('userName');
            }
            for (let s = 0; s < getDataFromLocalStorage.length; s++) {
                if (getDataFromLocalStorage[s].userName === name) {
                    var getScore = getDataFromLocalStorage[s].score;
                    dispatch(updateUserScore(getScore));
                }
            }
        }
    }
}

export const getNextCardsData = (data, displayData, score) => dispatch => {
    if (data.length > 0) {
        var payloadData = { getRandomData: [], isDisplayPlayButton: false, isDisplayNextButton: true, isDisplayNewGameButton: false };
        var getScore = score;
        var randomElement = data[Math.floor(Math.random() * data.length)];
        displayData = JSON.parse(JSON.stringify(displayData));
        displayData.push(randomElement);
        payloadData.getRandomData = displayData;
        payloadData.isDisplayPlayButton = false;
        payloadData.isDisplayNextButton = false;
        payloadData.isDisplayNewGameButton = false;
        if (displayData[0].name === displayData[1].name && Number(displayData[0].symbol) === Number(displayData[1].symbol)) {
            getScore = getScore + 10;
        } else if (displayData[0].name === displayData[1].name && Number(displayData[0].symbol) === Number(displayData[1].symbol) + 1 || Number(displayData[0].symbol) === Number(displayData[1].symbol) - 1) {
            getScore = getScore + 8;
        } else if (displayData[0].name === displayData[1].name) {
            getScore = getScore + 5;
        } else if (Number(displayData[0].symbol) === Number(displayData[1].symbol)) {
            getScore = getScore + 6;
        } else {
            getScore = getScore + 0;
        }
        dispatch(getFilteredData(payloadData));
        setTimeout(() => {
            dispatch(updateCardDisplayStatus(randomElement.code))
            dispatch(updateUserScore(getScore));
            payloadData.isDisplayNewGameButton = true;
            dispatch(updateGameStatus(payloadData.isDisplayNewGameButton));
        }, 1000);
    }
}

export const gotoScoreBoardPage = (props) => dispatch => {
    props.history.push('/score');
}

export default GamePageSlice.reducer;

import { createSlice } from '@reduxjs/toolkit';


export const StartPageSlice = createSlice({
    name: 'startpage',
    initialState: {
        userSelectedName: ''
    },
    reducers: {
        getUserName: (state, action) => {
            // Redux allows us to write "mutating" logic in reducers. It
            // doesn't actually mutate the state because it uses the Immer library,
            // which detects changes to a "draft state" and produces a brand new
            // immutable state based off those changes
            state.userSelectedName = action.payload;
        },

        resetUserName: (state, action) => {
            state.userSelectedName = '';
        },
    },
});

export const { getUserName, resetUserName } = StartPageSlice.actions;

// The function below can be dispatched like a regular action. This
// will call the thunk with the `dispatch` function as the first argument. All actions can be dispatched.
export const gotoGamePage = (name, props) => dispatch => {
    if (name !== "") {
        var getUsersDetails = {
            "userName": name,
            "score": 0
        };
        var getDataFromLocalStorage = [];
        getDataFromLocalStorage = JSON.parse(localStorage.getItem('userData'));
        if (getDataFromLocalStorage !== null && getDataFromLocalStorage.length > 0) {
            var getAllUserNames = [];
            for (let s = 0; s < getDataFromLocalStorage.length; s++) {
                if (getDataFromLocalStorage[s].userName === name) {
                    getUsersDetails.score = getDataFromLocalStorage[s].score
                    localStorage.setItem('userName', name);
                }
                getAllUserNames.push(getDataFromLocalStorage[s].userName);
            }
            if (getAllUserNames.indexOf(name) === -1) {
                getDataFromLocalStorage.push(getUsersDetails);
                localStorage.setItem('userName', name);
            }
            localStorage.setItem('userData', JSON.stringify(getDataFromLocalStorage));
        } else {
            localStorage.setItem('userName', name);
        }
        dispatch(getUserName(name));
        props.history.push('/game');
    }
}

export default StartPageSlice.reducer;

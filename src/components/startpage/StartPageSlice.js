import { createSlice } from '@reduxjs/toolkit';


export const StartPageSlice = createSlice({
  name: 'startpage',
  initialState: {
      userSelectedName: ''
  },
  reducers: {
      getUserName: (state, action) => {
          state.userSelectedName = action.payload;
      },

      resetUserName: (state, action) => {
          state.userSelectedName = '';
      },
  },
});

export const { getUserName, resetUserName } = StartPageSlice.actions;

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

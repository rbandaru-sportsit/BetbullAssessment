import { createSlice } from '@reduxjs/toolkit';


export const ScorePageSlice = createSlice({
  name: 'scorepage',
  initialState: {
      getAllUsersDetails : []
  },
  reducers: {
      getAllUsersDetails: (state, action) => {
          state.getAllUsersDetails = action.payload;
      },
  },
});

export const { getAllUsersDetails } = ScorePageSlice.actions;

export const goBacktToGamePage = (props) => dispatch => {
   props.history.push('/game');
}

export const goBacktToStartPage = (props) => dispatch => {
    window.location.href = '/start';
}

export const getUpdatedDataFromStorage = (props) => dispatch => {
    var getDataFromLocalStorage = [];
    getDataFromLocalStorage = JSON.parse(localStorage.getItem('userData'));
    if (getDataFromLocalStorage !== null && getDataFromLocalStorage.length > 0) {
        dispatch(getAllUsersDetails(getDataFromLocalStorage)); 
    }
}

export default ScorePageSlice.reducer;

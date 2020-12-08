import { configureStore } from '@reduxjs/toolkit';
import AppReducer from '.././AppSlice';
import StartPageReducer from '../components/startpage/StartPageSlice';
import GamePageReducer from '../components/gamepage/GamePageSlice';
import ScorePageReducer from '../components/scorepage/ScorePageSlice';

export default configureStore({
    reducer: {
        app: AppReducer,
        startpage: StartPageReducer,
        gamepage: GamePageReducer,
        scorepage: ScorePageReducer
  },
});

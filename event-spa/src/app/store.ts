import { configureStore } from "@reduxjs/toolkit";
import eventReducer from "../features/event/eventSlice";

export const store = configureStore({
  reducer: {
    event: eventReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
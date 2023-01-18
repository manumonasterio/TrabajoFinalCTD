import React, { createContext, useState } from 'react'

const Context = createContext()

export function DateContext({ children }) {
  const [rango, setRango] = useState([null, null])
  return (
    <Context.Provider value={{ rango, setRango }}>{children}</Context.Provider>
  )
}
export default Context
for (let index = 0; index < array.length; index++) {
  const element = array[index];
  
}
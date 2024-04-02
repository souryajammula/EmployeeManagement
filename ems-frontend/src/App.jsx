import './App.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'

import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponents from './components/ListEmployeeComponents'
import { BrowserRouter, Routes , Route } from 'react-router-dom'

function App() {
  

  return (
    <>
    <BrowserRouter>
      <HeaderComponent/>
        <Routes>
          <Route path="/" element={<ListEmployeeComponents/>}></Route>
          <Route path="/employees" element={<ListEmployeeComponents/>}></Route>
          <Route path="/add-employer" element={<EmployeeComponent/>}></Route>
          <Route path="/edit-employee/:id" element={<EmployeeComponent/>}></Route>
          

  

        </Routes>
      
      <FooterComponent/>
    </BrowserRouter>
    
    </>
  )
}

export default App

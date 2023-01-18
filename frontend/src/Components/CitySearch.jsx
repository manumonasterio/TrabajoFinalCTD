import React, { useState, useEffect } from 'react'
import '../Styles/CitySearch.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faLocationDot } from '@fortawesome/free-solid-svg-icons'
import getCities from '../services/cityService'

const CitySearch = ({ setCityId }) => {
  const [cities, setCities] = useState([])

  useEffect(() => {
    getCities().then((data) => {
      setCities(data.data)
    })
  }, [])

  return (
    <div className="city">
      <FontAwesomeIcon icon={faLocationDot} />
      <select
        name="cities"
        id="cities"
        onChange={(e) => {
          setCityId(e.target.value)
        }}
      >
        <option value="">¿A dónde vamos?</option>
        {cities.map((city) => {
          return (
            <option key={city.id} value={`${city.id}`}>
              {city.nombre}, {city.provincia.pais.nombre}
            </option>
          )
        })}
      </select>
    </div>
  )
}
export default CitySearch

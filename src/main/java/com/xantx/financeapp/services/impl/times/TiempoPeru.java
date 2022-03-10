package com.xantx.financeapp.services.impl.times;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.xantx.financeapp.services.TimeService;

import org.springframework.stereotype.Service;

@Service
public class TiempoPeru implements TimeService {

    @Override
    public String obtenerTiempoActual() throws Exception {
        Locale lugar = new Locale("es", "pe");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"), lugar);
        String fecha = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR)
                + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return fecha;
    }

}

package com.cloffystudio.ogretmenim.adapter;

import android.content.Context; // Uygulama bağlamını temsil eder
import android.view.LayoutInflater; // XML dosyalarını View objelerine dönüştürmek için kullanılır
import android.view.View; // Görünüm bileşenlerinin temel sınıfı
import android.view.ViewGroup; // Görünüm gruplarını temsil eder
import android.widget.TextView; // Metin göstermek için kullanılan View bileşeni

import androidx.annotation.NonNull; // Null olmayan parametreleri belirtmek için kullanılır
import androidx.recyclerview.widget.RecyclerView; // RecyclerView bileşeni

import com.cloffystudio.ogretmenim.R; // Kaynak dosyalarına erişim sağlar

import java.util.List; // Liste veri yapısını temsil eder

// RecyclerView için Adapter: Özlü sözlerin yatay bir liste olarak görüntülenmesini sağlar
public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private final Context context; // Uygulama bağlamı
    private final List<String> quotes; // Özlü sözlerin bulunduğu liste

    // Constructor: Adapter oluşturulurken bağlamı ve söz listesini alır
    public QuoteAdapter(Context context, List<String> quotes) {
        this.context = context; // Uygulama bağlamı atanır
        this.quotes = quotes;   // Söz listesi atanır
    }

    // Görünüm oluşturucu: Her bir liste öğesi için ViewHolder oluşturur
    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item_quote.xml dosyasını inflate ederek bir View oluşturur
        View view = LayoutInflater.from(context).inflate(R.layout.item_quote, parent, false);
        return new QuoteViewHolder(view); // ViewHolder oluşturup döndürür
    }

    // Verilerin ilgili liste öğesine bağlanmasını sağlar
    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        // Pozisyona göre ilgili özlü sözü alır
        String quote = quotes.get(position);

        // Alınan sözü TextView bileşenine atar
        holder.quoteTextView.setText(quote);
    }

    // Liste öğesi sayısını döndürür
    @Override
    public int getItemCount() {
        return quotes.size(); // Söz listesindeki toplam öğe sayısını döndürür
    }

    // ViewHolder: Her bir liste öğesi için View referanslarını tutar
    public static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView quoteTextView; // Sözlerin gösterileceği TextView bileşeni

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);

            // item_quote.xml'deki TextView bileşenini bağlar
            quoteTextView = itemView.findViewById(R.id.quoteTextView);
        }
    }
}
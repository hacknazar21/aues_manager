package com.example.auesmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auesmanager.pojo.TODO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoView extends RecyclerView.Adapter<TodoView.TodoViewHolder> {
    private List<TODO> todolist = new ArrayList<TODO>();
    private OnTodoClick onTodoClickListener;

    public TodoView(OnTodoClick onTodoClickListener){
        this.onTodoClickListener = onTodoClickListener;
    }
    @NonNull
    @Override
    public TodoView.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list, parent, false);
        return new TodoView.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoView.TodoViewHolder holder, int position) {
        holder.bind(todolist.get(position));
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView TextDo;
        private TextView TextDate;
        private ImageView ImageList;
        private RadioButton NoDo;
        private RadioButton YesDo;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            TextDo = itemView.findViewById(R.id.textDo);
            TextDate = itemView.findViewById(R.id.textDate);
            ImageList = itemView.findViewById(R.id.imageList);
            NoDo = itemView.findViewById(R.id.nodo);
            YesDo = itemView.findViewById(R.id.yesdo);
        }

        public void bind(TODO todo) {
            TextDo.setText(todo.getTextDo());
            TextDate.setText(todo.getTextDate());
            ImageList.setImageResource(todo.getImageLink());
            if (todo.getIsYes().equals("No")) NoDo.setChecked(true);
            else if (todo.getIsYes().equals("Yes")) YesDo.setChecked(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TODO todo = todolist.get(getLayoutPosition());
                    onTodoClickListener.onTodoClick(todo);
                }
            });
        }
    }
    public void setItem(TODO items){
        todolist.add(items);
        notifyItemChanged(getItemCount() - 1);
    }
    public void delItem(TODO item){
        todolist.remove(item);
        notifyDataSetChanged();
    }

    public void setItems(Collection<TODO> items){
        todolist.addAll(items);
        notifyDataSetChanged();
    }

    public interface OnTodoClick{
        void onTodoClick(TODO todo);
    }
}
